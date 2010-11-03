/*
 * IzPack - Copyright 2001-2008 Julien Ponge, All Rights Reserved.
 *
 * http://izpack.org/
 * http://izpack.codehaus.org/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.izforge.izpack.panels;

import com.izforge.izpack.gui.IzPanelLayout;
import com.izforge.izpack.gui.LabelFactory;
import com.izforge.izpack.installer.InstallData;
import com.izforge.izpack.installer.InstallerFrame;
import com.izforge.izpack.installer.IzPanel;
import com.izforge.izpack.util.AbstractUIProgressHandler;
import com.izforge.izpack.util.InstallPannelTailProcessor;
import com.izforge.izpack.util.JTailer;
import com.izforge.izpack.util.LogFileTailer;
import com.izforge.izpack.util.LogFileTailerListener;
import com.izforge.izpack.util.SummaryProcessor;
import com.izforge.izpack.util.TailerFrame;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * The install panel class. Launches the actual installation job.
 *
 * @author Julien Ponge
 */
public class InstallPanel extends IzPanel implements AbstractUIProgressHandler, LogFileTailerListener
{

    private static final long serialVersionUID = 3257282547959410992L;

    /**
     * The tip label.
     */
    protected JLabel tipLabel;

    /**
     * The operation label .
     */
    protected JLabel packOpLabel;

    /**
     * The operation label .
     */
    protected JLabel overallOpLabel;

    /**
     * The icon used.
     */
    protected String iconName = "preferences";

    /**
     * The pack progress bar.
     */
    protected JProgressBar packProgressBar;

    /**
     * The progress bar.
     */
    protected JProgressBar overallProgressBar;

    /**
     * True if the installation has been done.
     */
    private volatile boolean validated = false;

    /**
     * How many packs we are going to install.
     */
    private int noOfPacks = 0;

    protected JTextArea textArea;

    private LogFileTailer tailer;
    /**
     * The constructor.
     *
     * @param parent The parent window.
     * @param idata  The installation data.
     */
    public InstallPanel(InstallerFrame parent, InstallData idata)
    {
        super(parent, idata, new IzPanelLayout());
        this.tipLabel = LabelFactory.create(parent.langpack.getString("InstallPanel.tip"),
                parent.icons.getImageIcon(iconName), LEADING);
        add(this.tipLabel, IzPanelLayout.getDefaultConstraint(FULL_LINE_CONTROL_CONSTRAINT));
        packOpLabel = LabelFactory.create(" ", LEADING);
        add(packOpLabel, IzPanelLayout.getDefaultConstraint(FULL_LINE_CONTROL_CONSTRAINT));

        packProgressBar = new JProgressBar();
        packProgressBar.setStringPainted(true);
        packProgressBar.setString(parent.langpack.getString("InstallPanel.begin"));
        packProgressBar.setValue(0);
        add(packProgressBar, IzPanelLayout.getDefaultConstraint(FULL_LINE_CONTROL_CONSTRAINT));
        // make sure there is some space between the progress bars
        add(IzPanelLayout.createVerticalStrut(5));
        //add(IzPanelLayout.createParagraphGap());



        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setTabSize(4);
        JScrollPane scroller = new JScrollPane(textArea);
        add(scroller, BorderLayout.CENTER);


        overallOpLabel = LabelFactory.create(parent.langpack.getString("InstallPanel.progress"),
                parent.icons.getImageIcon(iconName), LEADING);
        add(this.overallOpLabel, IzPanelLayout.getDefaultConstraint(FULL_LINE_CONTROL_CONSTRAINT));

        overallProgressBar = new JProgressBar();
        overallProgressBar.setStringPainted(true);
        if (noOfPacks == 1)
        {
            overallProgressBar.setIndeterminate(true);
        }
        overallProgressBar.setString("");
        overallProgressBar.setValue(0);
        add(this.overallProgressBar, IzPanelLayout.getDefaultConstraint(FULL_LINE_CONTROL_CONSTRAINT));
        getLayoutHelper().completeLayout();
    }

    /**
     * Indicates wether the panel has been validated or not.
     *
     * @return The validation state.
     */
    public boolean isValidated()
    {
        return this.validated;
    }

    /**
     * The unpacker starts.
     */
    public void startAction(String name, int noOfJobs)
    {
        this.noOfPacks = noOfJobs;
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                parent.blockGUI();

                // figure out how many packs there are to install
                overallProgressBar.setMinimum(0);
                overallProgressBar.setMaximum(noOfPacks);
                overallProgressBar.setString("0 / " + Integer.toString(noOfPacks));
            }
        });
    }

    /**
     * An error was encountered.
     *
     * @param error The error text.
     */
    public void emitError(String title, String error)
    {
        this.packOpLabel.setText(error);
        idata.installSuccess = false;
        JOptionPane optionPane = getNarrowOptionPane(100);
        optionPane.setMessage(error);
        optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog(this, parent.langpack.getString("installer.error"));
        dialog.setVisible(true);
    }

    public static JOptionPane getNarrowOptionPane(final int maxCharactersPerLineCount) {
        // inner class definition
        class NarrowOptionPane extends JOptionPane {
            int maxCharactersPerLineCount;
            NarrowOptionPane(final int maxCharactersPerLineCount) {
                this.maxCharactersPerLineCount = maxCharactersPerLineCount;
            }
            public int getMaxCharactersPerLineCount() {
                return maxCharactersPerLineCount;
            }
        }
        return new NarrowOptionPane(maxCharactersPerLineCount);
    }

    /**
     * The unpacker stops.
     */
    public void stopAction()
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                parent.releaseGUI();
                parent.lockPrevButton();

                // With custom actions it is possible, that the current value
                // is not max - 1. Therefore we use always max for both
                // progress bars to signal finish state.
                overallProgressBar.setValue(overallProgressBar.getMaximum());
                int ppbMax = packProgressBar.getMaximum();
                if (ppbMax < 1)
                {
                    ppbMax = 1;
                    packProgressBar.setMaximum(ppbMax);
                }
                packProgressBar.setValue(ppbMax);

                packProgressBar.setString(parent.langpack.getString("InstallPanel.finished"));
                packProgressBar.setEnabled(false);
                String no_of_packs = Integer.toString(noOfPacks);
                if (noOfPacks == 1)
                {
                    overallProgressBar.setIndeterminate(false);
                }
                overallProgressBar.setString(no_of_packs + " / " + no_of_packs);
                overallProgressBar.setEnabled(false);
                packOpLabel.setText(" ");
                packOpLabel.setEnabled(false);
                idata.canClose = true;
                validated = true;
                if (idata.panels.indexOf(this) != (idata.panels.size() - 1))
                {
                    parent.unlockNextButton();
                }
            }
        });
    }

    /**
     * Normal progress indicator.
     *
     * @param val The progression value.
     * @param msg The progression message.
     */
    public void progress(final int val, final String msg)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                packProgressBar.setValue(val + 1);
                packOpLabel.setText(msg);
            }
        });
    }

    /**
     * Pack changing.
     *
     * @param packName The pack name.
     * @param stepno   The number of the pack.
     * @param max      The new maximum progress.
     */
    public void nextStep(final String packName, final int stepno, final int max)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                packProgressBar.setValue(0);
                packProgressBar.setMinimum(0);
                packProgressBar.setMaximum(max);
                packProgressBar.setString(packName);
                overallProgressBar.setValue(stepno - 1);
                overallProgressBar.setString(Integer.toString(stepno) + " / "
                        + Integer.toString(noOfPacks));
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public void setSubStepNo(final int no_of_substeps)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                packProgressBar.setMaximum(no_of_substeps);
            }
        });
    }

    /**
     * Called when the panel becomes active.
     */
    public void panelActivate()
    {
        // We clip the panel
        Dimension dim = parent.getPanelsContainerSize();
        dim.width -= (dim.width / 4);
        dim.height = 150;
        setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);
        parent.lockNextButton();

        parent.install(this);

        File logDir = new File(idata.getVariable("SYSTEM_user_home") + "/"
                + idata.getVariable("installer.dir") + "/");
        if (!logDir.exists())
            new File(idata.getVariable("SYSTEM_user_home") + "/"
                    + idata.getVariable("installer.dir")).mkdirs();

        String fileName = new String(idata.getVariable("SYSTEM_user_home")
                + "/" + idata.getVariable("installer.dir")
                + "/antlog_installer.txt");
        File logFile = new File(fileName);
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        tailer = new LogFileTailer(logFile, 1000, false);
        tailer.addLogFileTailerListener(this);
        tailer.start();

        // Will tail the log in new frame
        // JTailer frame = new JTailer(fileName);

    }

    public void newLogFileLine(String line) {
        this.textArea.append(line);
        this.textArea.setCaretPosition(this.textArea.getDocument().getLength());

    }

}
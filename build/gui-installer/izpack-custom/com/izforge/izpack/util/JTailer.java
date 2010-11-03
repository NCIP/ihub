package com.izforge.izpack.util;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;


public class JTailer extends JFrame implements ActionListener
{
  private JDesktopPane desktop = new JDesktopPane();
  private JMenuBar menuBar = new JMenuBar();
  private JMenuItem menuAddTailer = new JMenuItem( "Add Log Tailer" );

  public JTailer()
  {
    init();
  }

  public JTailer( String[] files )
  {
    init();
    for( int i=0; i<files.length; i++ )
    {
      String filename = files[ i ];
      addTailer( filename );
    }
  }

  private void addTailer( String filename )
  {
    System.out.println( "Tailing: " + filename );
    TailerFrame frame = new TailerFrame( filename );
    this.desktop.add( frame );
  }

  private void init()
  {
    // Setup the menu
    JMenu tailMenu = new JMenu( "Tailer" );
    tailMenu.add( menuAddTailer );
    this.menuAddTailer.addActionListener( this );
    this.menuBar.add( tailMenu );
    this.setJMenuBar( this.menuBar );

    this.setTitle( "JTailer" );
    this.getContentPane().add( desktop, BorderLayout.CENTER );
    this.setSize( 1000, 700 );
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation( d.width / 2 - 500, d.height / 2 - 350 );
    this.setVisible( true );
    this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
  }

  /**
   * Invoked when an action occurs.
   */
  public void actionPerformed(ActionEvent e)
  {
    if( e.getSource() == this.menuAddTailer )
    {
      JFileChooser fc = new JFileChooser();
      int retval = fc.showOpenDialog( this );
      if( retval == JFileChooser.APPROVE_OPTION )
      {
        File f = fc.getSelectedFile();
        this.addTailer( f.getAbsolutePath() );
      }
    }
  }

  public static void main( String[] args )
  {
    JTailer tailer = null;
    if( args.length == 0 )
    {
      tailer = new JTailer();
    }
    else
    {
      tailer = new JTailer( args );
    }
  }
}
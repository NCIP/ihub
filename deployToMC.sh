export MIRTH_HOME=$1
echo "Rohit Gupta - Inside deployToMC"
echo $MIRTH_HOME
$MIRTH_HOME/mccommand -s $2

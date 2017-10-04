# Gnu Makefile for CollisionApplet.java
# Drew Dolgert ajd2m@virginia.edu
# September, 1998
#
# I put everything in the FILES section and filter out what I need to compile
# so there is a record of what's important in the directory.
FILES = makefile Collision.java Arrow.java AngleCanvas.java BallCanvas.java \
	AngleCalculator.java CollisionApplet.java Main.java LPanel.java \
	BufferedPanel.java OvalCanvas.java CollisionGraph.java \
	CollisionColors.java TurnedLabel.java RotFilter.java DoubleTextField.java \
	DoubleListener.java CustomScrollBar.java CenteredRatioBar.java \
	MultiLineLabel.java CollisionState.java CmdCheckBox.java \
	changes.html collisions.tex collisions.dvi collisions.ps \
	notes.html messages.gif angles.eps nojar.html

JFILES = $(filter %.java, $(FILES))
OBJS = $(JFILES:%.java=%.class)

CFLAGS = -g

.PHONY.: clean zip jar

# This is a general rule to make class files.
# While using "javac CollisionApplet.java" will automatically track down every
# outdated class file, the makefile tends to be much faster.
%.class: %.java
	javac $(CFLAGS) $<

applet.html: $(OBJS)
	touch applet.html

clean:
	-rm *.'$$$' *.log

zip:
	zip collision.zip $(FILES) *.properties

jar:
	jar -cvf collision.jar *.class *.properties



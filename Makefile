# binary search program makefile

JFLAGS = -g
JC = javac
SRCDIR= src
BINDIR= bin
DOCDIR = doc
DATADIR = data

.SUFFIXES: .java .class
.java.class:
	$(C) $(JFLAGS) -cp $(BINDIR) $*.java -d $(BINDIR)

CLASSES = $(SRCDIR)/*.java

default: classes

classes:
	$(JC) $(JFLAGS) -cp $(BINDIR) $(CLASSES) -d $(BINDIR)

clean:
	$(RM) $(BINDIR)/*.class

run:
	java -cp $(BINDIR) GenericsKbAVLApp

javadoc:
	javadoc -d $(DOCDIR) $(SRCDIR)/*.java

docs:
	javadoc -d doc/ src/*.java

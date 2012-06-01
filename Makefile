JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        EchoServer.java \
        EchoClient.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

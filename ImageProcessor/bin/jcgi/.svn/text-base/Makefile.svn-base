OBJS=		jcgi.o
TARGET=		jcgi

all:	$(TARGET)

clean:
	rm -f $(OBJS) $(TARGET) *~

$(TARGET):	$(OBJS)
	$(CC) -o $(TARGET) $(OBJS)

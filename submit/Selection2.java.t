class
Selection2
extends
eecs
.
Gui
{
public
static
void
main
(
String
[
]
args
)
{
int
club
=
0
;
int
distance
=
getInt
(
"
Enter
distance
to
hole
"
)
;
if
(
distance
>
145
)
{
club
=
club
+
6
;
}
if
(
distance
>
=
121
&&
distance
<
=
145
)
{
club
=
club
+
5
;
}
if
(
distance
<
=
120
&&
distance
>
=
81
)
{
club
=
club
+
4
;
}
if
(
distance
<
=
80
)
{
club
=
club
+
3
;
}
int
elevation
=
getInt
(
"
Enter
elevation
from
hole
"
)
;
if
(
elevation
>
15
)
{
club
=
club
+
1
;
}
if
(
elevation
<
-15
)
{
club
=
club
-
1
;
}
else
{
club
=
club
+
0
;
}
double
wind
=
getDouble
(
"
Enter
wind
speed
"
)
;
if
(
wind
<
0
)
{
double
windFactor
=
(
(
Math
.
pow
(
wind/10,2
)
)
+Math
.
sqrt
(
5
)
)
;
if
(
windFactor
<
=
5
)
{
club
=
club
+
0
;
}
if
(
windFactor
>
5
)
{
club
=
club
+
1
;
}
}
else
{
double
windFactor
=
(
(
Math
.
pow
(
wind
/10,2
)
)
+Math
.
sqrt
(
5
)
)
;
if
(
windFactor
<
=
5
)
{
club
=
club
+
0
;
}
if
(
windFactor
>
5
)
{
club
=
club
-
1
;
}
}
if
(
club
=
=
7
)
{
printLine
(
"
8I
"
)
;
}
if
(
club
=
=
6
)
{
printLine
(
"
9I
"
)
;
}
if
(
club
=
=
5
)
{
printLine
(
"
PW
"
)
;
}
if
(
club
=
=
4
)
{
printLine
(
"
SW
"
)
;
}
if
(
club
=
=
3
)
{
printLine
(
"
LW
"
)
;
}
if
(
club
=
=
2
)
{
printLine
(
"
LWII
"
)
;
}
if
(
club
=
=
1
)
{
printLine
(
"
FS
"
)
;
}
     

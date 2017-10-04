Here is what the pieces do:

1) MainServlet.java accepts all requests, if they are
actions(createAccount or readAccount actions) then process request in
Handler, and finally forward to the correct new page.
2) AccountHandler.java  - accepts the HTTP request and converts it to
java requests, calls BusinesDelegate to access customer component
3) BusinessDelegate access customer component, and populates a
ResultBean with data that is by the jsp pageshowaccount.jsp to show the
results.
4) ResultBean is just a bean used by the jsp pages to hold data.



TO USE THIS APP

1) run setup to create the AccountDB
2) ant to build
3) ant deploy to deploy
4) point browser to
              http://localhost:8000/customer
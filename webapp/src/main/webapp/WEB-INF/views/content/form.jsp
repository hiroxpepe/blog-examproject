<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="left-content">
    <div class="block form-content">

        <div id="message-block">
        </div>

        <%-- in this form, the normal Http post is NOT used. --%>
        <%-- all of the data will request using Ajax. --%>
        <form:form id="entry-form" modelAttribute="entryForm">
            <form:hidden path="id" />
            <form:hidden path="code" />
            <%-- insert the entry content template. --%>
            <tiles:insertTemplate template="/WEB-INF/views/content/_entryContent.jsp" />
            <%-- insert the tab content template. --%>
            <tiles:insertTemplate template="/WEB-INF/views/content/_tabContent.jsp" />
        </form:form>
    </div>
</div>

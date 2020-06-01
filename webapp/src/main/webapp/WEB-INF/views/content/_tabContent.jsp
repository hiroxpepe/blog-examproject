<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="tab-content">
    <ul>
        <li><a href="#tab1"><fmt:message key="label.list" /></a></li>
        <li><a href="#tab2"><fmt:message key="label.setting" /></a></li>
    </ul>
    <div id="tab1" class="tab">
        <div
            id="entry-list-block"
            class="entry-list-content">
        </div>
    </div>
    <div id="tab2" class="tab">
        <div class="setting-content">
            <table class="setting-table">
                <tr>
                    <td class="label-td">
                        <label for="entry_username">
                            <fmt:message key="entry.form.username" />
                        </label>
                    </td>
                    <td class="input-td">
                        <form:input id="entry_username" path="username" />
                    </td>
                </tr>
                <tr>
                    <td class="label-td">
                        <label for="entry_password">
                            <fmt:message key="entry.form.password" />
                        </label>
                    </td>
                    <td class="input-td">
                        <form:password id="entry_password" path="password" showPassword="true" />
                    </td>
                </tr>
                <tr>
                    <td class="label-td">
                        <label for="entry_email">
                            <fmt:message key="entry.form.email" />
                        </label>
                    </td>
                    <td class="input-td">
                        <form:input id="entry_email" path="email" />
                    </td>
                </tr>
            </table>
            <div class="setting-command-block">
                <input id="setting-button"
                       class="command-button"
                       type="button"
                       value="<fmt:message key="button.setting" />"
                 />
            </div>
        </div>
    </div>
</div>

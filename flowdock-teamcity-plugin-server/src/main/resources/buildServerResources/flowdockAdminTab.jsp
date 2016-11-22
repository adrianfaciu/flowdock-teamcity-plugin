<%@ include file="/include.jsp"%>

<div id="settingsContainer">
    <form method="post" action="/flowdockNotifier/flowdockAdminTab.html">
        <div class="editNotificatorSettingsPage">

            <c:choose>
                <c:when test="${disabled}">
                   <div class="pauseNote" style="margin-bottom: 1em;">
                        The notifier is <strong>disabled</strong>&nbsp;&nbsp; <a class="btn btn_mini" href="#" id="enable-btn">Enable</a>
                    </div>
                 </c:when>
                 <c:otherwise>
                    <div style="margin-left: 0.6em;">
                         The notifier is <strong>enabled</strong>&nbsp;&nbsp;<a class="btn btn_mini" href="#" id="disable-btn">Disable</a>
                     </div>
                 </c:otherwise>
             </c:choose>

            <br/>

            <table class="runnerFormTable">
                 <tr class="groupingTitle">
                      <td colspan="2">General Configuration</td>
                </tr>
                <tr>
                      <th>
                           <label for="token">Source token: <l:star /></label>
                       </th>
                       <td>
                           <forms:textField name="token" value="${token}" style="width: 300px;" />
                           <span class="smallNote">Source token from FlowDock. Add docs link...</span>
                       </td>
                </tr>
            </table>
            <div class="saveButtonsBlock">
                    <forms:submit label="Save" />
                    <forms:saving />
            </div>
         </div>
    </form>
</div>

<!-- use ${teamcityPluginResourcesPath} to get path -->
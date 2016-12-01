<%@ include file="/include.jsp"%>

    <h4>Configure notifications: </h4>
    <h5>In the top right corner of the screen, click the arrow next to your username, and select My Settings & Tools from the drop-down list. Open the Notification Rules tab and select Flowdock notifications</h5>
    <h5>To change the flow where the notification is posted use a different source below, otherwise it will use the one from project config or from admin settings.</h5>

    <br/>

    <form method="post" action="/flowdockNotifier/flowdockAdminTab.html" id="flowdockAdminForm">
        <div class="editNotificatorSettingsPage">
            <table class="runnerFormTable">
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
                    <forms:submit type="button" label="Save" onclick="return flowdockAdmin.save()"/>
                    <forms:saving />
            </div>
         </div>
    </form>
<%@ include file="/include.jsp"%>
    <bs:linkCSS>
        ${teamcityPluginResourcesPath}lib/jquery-ui.min.css
    </bs:linkCSS>
    <bs:linkScript>
        ${teamcityPluginResourcesPath}lib/jquery-ui.min.js
    </bs:linkScript>
    <bs:linkScript>
        ${teamcityPluginResourcesPath}scripts/dialogManager.js
    </bs:linkScript>

    <div>
        <form method="post" action="/flowdockNotifier/flowdockController.html" id="flowdockProjectForm">
            <div class="editNotificatorSettingsPage">
                <table class="runnerFormTable">
                    <tr>
                        <th>
                            <label for="token">Source token: <l:star/></label>
                        </th>
                        <td>
                            <forms:textField name="token" value="${token}" style="width: 300px;" />
                            <span class="smallNote">Source token from <a href="https://www.flowdock.com/api/sources" target="_blank">FlowDock</a>, for this project.</span>
                        </td>
                    </tr>
                </table>
                <div class="saveButtonsBlock">
                    <forms:submit type="button" label="Save" onclick="return flowdockAdmin.saveProject()" />
                    <forms:saving />
                </div>
            </div>
            <input type="hidden" id="projectId" name="projectId" value="${projectId}" />
        <div id="fl-dialog" title="Flowdock plugin">
             <p>Paste message here</p>
        </div>
        </form>
        <bs:linkScript>
            ${teamcityPluginResourcesPath}scripts/flowdockAdmin.js
        </bs:linkScript>
    </div>
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

    <div id="settingsContainer">
        <form method="post" action="/flowdockNotifier/flowdockController.html" id="flowdockAdminForm">
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
                            <span class="smallNote">Source token from <a href="https://www.flowdock.com/api/sources" target="_blank">FlowDock</a>.</span>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="token">Enable logger info messages:</label>
                        </th>
                        <td>
                            <forms:checkbox name="enableLogInfoMessages" checked="${enableLogInfoMessages}" value="${enableLogInfoMessages}"/>
                            <span class="smallNote">Enable plugin debugging</span>
                        </td>
                    </tr>
                </table>
                <div class="saveButtonsBlock">
                    <forms:submit type="button" label="Save" onclick="return flowdockAdmin.save()" />
                    <forms:saving />
                </div>
            </div>
        </form>
        <div>
            <p>For instructions on how to use the plugin <a href="https://github.com/adrianfaciu/flowdock-teamcity-plugin/blob/master/HOWTO.md">check here.</a></p>
        </div>
        <div id="fl-dialog" title="Flowdock plugin">
            <p>Paste message here</p>
        </div>
        <bs:linkScript>
            ${teamcityPluginResourcesPath}scripts/flowdockAdmin.js
        </bs:linkScript>
    </div>

    <script type="text/javascript">
	(function($) {
       $("#enable-btn").click(function() {
            return flowdockAdmin.saveEnabled(true);
       });
        $("#disable-btn").click(function() {
            return flowdockAdmin.saveEnabled(false);
        });
    })(jQuery);
</script>
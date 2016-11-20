<div id="settingsView">
    <form method="post" >
        <table>
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
        <div>
            <forms:submit label="Save" />
            <forms:saving />
        </div>
    </form>
</div>

<!-- use ${teamcityPluginResourcesPath} to get path -->
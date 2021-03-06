

var flowdockAdmin = {
    save: function () {
        if (!flowdockAdmin.validate()) {
            showFlowdockDialog("Cannot save. Invalid values!");
            return false;
        }
        jQuery.ajax({
            url: $("flowdockAdminForm").action,
            data: {
                action: "editSettings",
                token: $("token").value,
                enableLogInfoMessages: $("enableLogInfoMessages").value
            },
            type: "POST"
        }).done(function () {
            showFlowdockDialog("Settings saved!");
        });

        return;
    },

    validate: function () {
        var valid = true;
        valid = valid || ($("token").value && $("token").value.length > 0);

        return valid;
    },

    saveEnabled: function (isEnabled) {
        jQuery.ajax({
            url: $("flowdockAdminForm").action,
            data: {
                action: "changeEnabled",
                flag: isEnabled
            },
            type: "POST"
        }).done(function () {
            BS.reload(true);
        });

        return false;
    },

    saveProject: function () {
        jQuery.ajax({
            url: $("flowdockProjectForm").action,
            data: {
                action: "saveProjectSettings",
                token: $("token").value,
                projectId: $("projectId").value
            },
            type: "POST"
        }).done(function () {
            showFlowdockDialog("Project settings saved!");
        });
    }
};
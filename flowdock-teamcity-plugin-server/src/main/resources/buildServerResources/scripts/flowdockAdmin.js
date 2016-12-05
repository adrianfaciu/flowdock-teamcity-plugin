

var flowdockAdmin = {
    save: function () {
        if (!flowdockAdmin.validate()) {
            showFlowdockDialog("Cannot save. Invalid values!");
            return false;
        }
        jQuery.ajax({
            url: jQuery("flowdockAdminForm").action,
            data: {
                action: "editSettings",
                token: jQuery("token").value
                enableLogInfoMessages: jQuery("enableLogInfoMessages").value
            },
            type: "POST"
        }).done(function () {
            showFlowdockDialog("Settings saved!");
        });

        return;
    },

    validate: function () {
        var valid = true;
        valid = valid || (jQuery("token").value && jQuery("token").value.length > 0);

        return valid;
    },

    saveEnabled: function (isEnabled) {
        jQuery.ajax({
            url: jQuery("flowdockAdminForm").action,
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
            url: jQuery("flowdockProjectForm").action,
            data: {
                action: "saveProjectSettings",
                token: jQuery("token").value,
                projectId: jQuery("projectId").value
            },
            type: "POST"
        }).done(function () {
            showFlowdockDialog("Project settings saved!");
        });
    }
};
var flowdockAdmin = {
    save : function() {
           if (!flowdockAdmin.validate()) {
             alert("Cannot save. Invalid values!");
             return false;
           }
          jQuery.ajax({
                        url: $("flowdockAdminForm").action,
                        data: {
                                action: "editSettings",
                                token: $("token").value },
                        type: "POST"
                     }).done(function() {
                        alert("Settings saved!");
                     });

        return ;
    },

    validate() : function() {
        var valid = true;
        valid = valid || ($("token").value && $("token").value.length > 0);

        return valid;
    },

    saveEnabled : function(isEnabled) {
         jQuery.ajax({
                        url: $("flowdockAdminForm").action,
                        data: {
                                action: "changeEnabled",
                                flag: isEnabled },
                        type: "POST"
                      }).done(function() {
                         BS.reload(true);
                      });

        return false;
    }
};
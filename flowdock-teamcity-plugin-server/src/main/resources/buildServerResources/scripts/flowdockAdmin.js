var flowdockAdmin = {
    save : function() {
           if (!flowdockAdmin.validate()) {
             alert("Cannot save. Invalid values!");
             return false;
           }
          jQuery.ajax({
                        url: $("flowdockAdminForm").action,
                        data: {
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
        // do ajax post - then ->
        console.log('saving isEnabled');

        BS.reload(true);

        return false;
    }
};
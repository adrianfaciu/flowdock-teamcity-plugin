var flowdockAdmin = {
    save : function() {
          console.log('saving');

          jQuery.ajax({
                        url: $("flowdockAdminForm").action,
                        data: {
                                token: $("token").value },
                        type: "POST"
                     }).done(function() {
                        console.log('post done');
                     });

        return ;
    }
};
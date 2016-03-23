/**
 * Created by inet2005 on 1/7/16.
 */
function deleteTemplate(id) {
    if (confirm('Delete this template?')) {
        $.ajaxSetup({
            headers: {
                'X-CSRF-Token': $('meta[name="csrf-token"]').attr('content')
            }
        });
        $.ajax({
            type: "DELETE",
            url: 'csstemplates/' + id,
            success: function(affectedRows) {
                if (affectedRows > 0) window.location = 'csstemplates';
            }
        });
    }
}
function deleteWebPage(id) {
    if (confirm('Delete this page?')) {
        $.ajaxSetup({
            headers: {
                'X-CSRF-Token': $('meta[name="csrf-token"]').attr('content')
            }
        });
        $.ajax({
            type: "DELETE",
            url: 'webpages/' + id,
            success: function(affectedRows) {
                if (affectedRows > 0) window.location = 'webpages';
            }
        });
    }
}
function deleteContentArea(id) {
    if (confirm('Delete this content area?')) {
        $.ajaxSetup({
            headers: {
                'X-CSRF-Token': $('meta[name="csrf-token"]').attr('content')
            }
        });
        $.ajax({
            type: "DELETE",
            url: 'contentareas/' + id,
            success: function(affectedRows) {
                if (affectedRows > 0) window.location = 'contentareas';
            }
        });
    }
}
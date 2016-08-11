
$(document).ready(function () {
  $('.ui.dropdown.TenantDelete').dropdown();
  $('.ui.dropdown.LandlordDelete').dropdown();

  $('.ui.form').form({
    fields: {
      LandlordDelete: {
        identifier: 'Landlord_ID',
        rules: [{
          type: 'empty',
          prompt: 'Please select landlord to Delete'
        }]
      },

      TenantDelete: {
        identifier: 'Tenant_ID',
        rules: [{
          type: 'empty',
          prompt: 'Please select tenant to Delete'
        }]
      }
    }
  });
});
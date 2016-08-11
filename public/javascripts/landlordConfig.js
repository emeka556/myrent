$(document).ready(function () {
  $('#EditResidence').dropdown();

  $('#DeleteResidence').dropdown();

  $('.ui.form').form({
    fields: {
      DeleteResidence: {
        identifier: 'eircode',
        rules: [{
          type: 'empty',
          prompt: 'Please Select from the dropdown Residence to delete'
        }]
      },

      EditResidence: {
        identifier: 'geolocation',
        rules: [{
          type: 'empty',
          prompt: 'Please Select residence to edit from dropdown '
        }]
      }
    }
  });
});
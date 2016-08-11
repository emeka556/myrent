$('.ui.checkbox').checkbox('');

$('.ui.form').form({
  fields: {

    rent: {
      identifier: 'rent',
      rules: [{
        type: 'empty',
        prompt: 'Please enter rent amount to update'
      }]
    }
  }
});
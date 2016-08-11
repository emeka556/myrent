$('.ui.dropdown.newTenancy').dropdown('');

$('.ui.form').form({
  fields: {

    newResidence: {
      identifier: 'newResidence',
      rules: [{
        type: 'empty',
        prompt: 'Please select a vacant residence'
      }]
    }
  }
});
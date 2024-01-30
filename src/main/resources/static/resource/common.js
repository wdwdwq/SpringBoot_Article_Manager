$('select[data-value]').each(function(index, item){
	
	console.log(index);
	
	const items = $(item);
	
	const itemValue = items.attr('data-value').trim();
	
	if (itemValue.length > 0) {
		items.val(itemValue);
	}
})
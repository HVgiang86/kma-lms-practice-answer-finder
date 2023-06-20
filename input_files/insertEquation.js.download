function InsertCodeInTextArea(textValue) {

	// Get textArea HTML control
	var txtArea = document.getElementById("txtTextArea");

	// IE
	if (document.selection) {
		txtArea.focus();
		var sel = document.selection.createRange();
		sel.text = textValue;
		return;
	}
	// Firefox, chrome, mozilla
	else if (txtArea.selectionStart || txtArea.selectionStart == '0') {
		var startPos = txtArea.selectionStart;
		var endPos = txtArea.selectionEnd;
		var scrollTop = txtArea.scrollTop;
		txtArea.value = txtArea.value.substring(0, startPos) + textValue
				+ txtArea.value.substring(endPos, txtArea.value.length);
		txtArea.focus();
		txtArea.selectionStart = startPos + textValue.length;
		txtArea.selectionEnd = startPos + textValue.length;
	} else {
		txtArea.value += textArea.value;
		txtArea.focus();
	}

}
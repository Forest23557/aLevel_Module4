'use strict';

document.addEventListener('DOMContentLoaded', () => {
	const createButton = document.querySelector("button"),
		section = document.querySelector("section");

	createButton.addEventListener("click", () => {
		section.innerHTML = `
   		<div>Wait until a detail is created.</div>
   		<div>You will redirect to a page with information about the detail.</div>
   	`;
	});
});
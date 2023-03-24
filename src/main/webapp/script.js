'use strict';

document.addEventListener('DOMContentLoaded', () => {
	const createButton = document.querySelector("button"),
			section = document.querySelector("section"),
			container = document.createElement("div");

	container.classList.add("redirect-message");

	createButton.addEventListener("click", () => {
		container.innerHTML = `
			<div class="spinner"></div>
			<div>Wait until a detail is created.</div>
			<div>You will be redirected to a page with information about the detail.</div>
		`;

		document.body.insertBefore(container, section);
		section.classList.add("hidden");
	});
});
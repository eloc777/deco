function initAccordion(containerSelector) {
    const accordions = document.querySelectorAll(containerSelector);

    accordions.forEach((container) => {
        const headers = container.querySelectorAll(".accordion-header");
        headers.forEach((header) => {
            header.addEventListener("click", () => {
                const item = header.parentElement; // get the accordion (parent of the header)
                item.classList.toggle("open");//toggles open class for accordion item
            });
        });
    });
}

export { initAccordion };

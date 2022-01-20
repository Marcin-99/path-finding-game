const addCollapseEffect = (clickedId, collapsedId) => {
    document.getElementById(clickedId).addEventListener('click', () => {
        const sourceCodeContent = document.getElementById(collapsedId)
        const display = sourceCodeContent.style.display
        sourceCodeContent.style.display = display === 'none' || display === '' ? 'flex' : 'none'
    })
}

addCollapseEffect("sourceCodeLabel", "sourceCodeContent")
addCollapseEffect("functionalityLabel", "functionalityContent")
addCollapseEffect("rulesSectionLabel", "rulesContent")
addCollapseEffect("colorsLabel", "colorsContent")
addCollapseEffect("classesAndFunctionsLabel", "classesAndFunctionsContent")

const tableau = new Array("reaction", "memory", "verbal", "visual");

async function jsonData() {
  try {
    const response = await fetch("./data.json");
    const eltJson = await response.json();

    let variable = eltJson;
    // console.log("dans le fetch :", variable);
    return variable;
  } catch (error) {
    console.error("Erreur de lecture JSON:", error);
  }
}

jsonData().then((elt) => {
  console.log(elt);
});

async function select_item() {
  const elmt = await jsonData();

  const tableau = new Array("reaction", "memory", "verbal", "visual");
  for (var i = 0; i < tableau.length; i++) {
    let img = document.createElement("img");
    let item = document.getElementsByClassName(tableau[i])[0];
    let p_category = document.createElement("p");
    let p_score = document.createElement("p");
    let texte_p_category = document.createTextNode(elmt[i].category);
    let texte_p_score = document.createTextNode(elmt[i].score + " / 100");
    img.setAttribute("src", elmt[i].icon);
    item.appendChild(img);
    p_category.appendChild(texte_p_category);
    item.appendChild(p_category);
    p_score.appendChild(texte_p_score);
    item.appendChild(p_score);
  }
}

select_item();

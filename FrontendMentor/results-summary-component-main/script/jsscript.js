const tableau = new Array("reaction", "memory", "verbal", "visual");
async function jsonData() {
  try {
    const response = await fetch("./data.json");
    const eltJson = await response.json();

    let variable = eltJson;
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

  let moyTotal = 0;
  const tableau = new Array("reaction", "memory", "verbal", "visual");
  for (var i = 0; i < tableau.length; i++) {
    let img = document.createElement("img");
    let item = document.getElementsByClassName(tableau[i])[0];
    let p_category = document.createElement("p");
    let p_score = document.createElement("p");
    let texte_p_category = document.createTextNode(elmt[i].category);
    let texte_p_score = document.createTextNode(elmt[i].score + " / 100");
    moyTotal += elmt[i].score;
    img.setAttribute("src", elmt[i].icon);
    item.appendChild(img);
    p_category.appendChild(texte_p_category);
    item.appendChild(p_category);
    p_score.appendChild(texte_p_score);
    item.appendChild(p_score);
  }
  moyTotal = Math.round(moyTotal / tableau.length);
  console.log(moyTotal);
  let result = document.getElementsByClassName("summary")[0];
  let details = document.getElementsByClassName("detail")[0];
  result.innerHTML = moyTotal;
  if (moyTotal < 65) {
    details.innerHTML =
      "You scored Lower than 65% of the people who have taken these tests.";
  } else if (moyTotal == 65) {
    details.innerHTML =
      "You scored 65% like many other people who have taken these tests.";
  }
}

window.addEventListener("load", select_item);

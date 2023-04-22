function deepEquals(obj1, obj2) {
    let chavesA = Object.keys(obj1);
    let chavesB = Object.keys(obj2);
  
    if (chavesA.length !== chavesB.length) {
      return false;
    }
  
    for (let chave of chavesA) {
      let valorA = obj1[chave];
      let valorB = obj2[chave];
  
      let saoObjetos = ehObjeto(valorA) && ehObjeto(valorB);
      if ((saoObjetos && !deepEquals(valorA, valorB)) || (!saoObjetos && (valorA !== valorB))) {
        return false;
      }
    }
  
    return true;
  }
  
  function ehObjeto(obj) {
    return obj != null && typeof obj === "object";
  }
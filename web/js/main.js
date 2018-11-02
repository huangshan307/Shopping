shoesSizesPrice = new Map();

$(document).ready(function() {
  // setSelectedOption();
  selectPriceInfo();
});

// function setSelectedOption() {
//   $(document).on("change", "select", function() {
//     $("option[value=" + this.value + "]", this)
//       .attr("selected", true)
//       .siblings()
//       .removeAttr("selected");
//   });
// }

function selectPriceInfo() {
  $("select").on("click", function() {
    var shoesID = $(this).attr("id");
    // console.log("Shoes ID: " + shoesID);
    var selectedOption = $("option[value=" + this.value + "]", this).val();
    // console.log("Sizes ID: " + selectedOption);
    var priceList = shoesSizesPrice.get(shoesID);
    if (priceList != null) {
        var price = priceList.get(selectedOption);
        $("#showPrice" + shoesID).html(price);
    } else {
        $("#showPrice" + shoesID).html("");
    }
    
  });
}

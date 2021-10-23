function deleteCompte(rib)
{



swal({
  title: "ta7ki berasmi?",
  text: "ah 7ouma t7eb tfas5!",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((willDelete) => {

						 if (willDelete) {
						 $.ajax({
					type: "GET",
					url: "/compte/delete-ajax",
					data: { 'rib': rib },
					success: function() {
						//alert("ok");
						$('#'+rib).remove();
    swal("ah 7ouma t7eb tfasa5!", {
      icon: "success",
    })
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						alert(XMLHttpRequest.responseText);
					}


				});
 
  } else {
    swal("annuler de suprrimer!");
  }
});
}
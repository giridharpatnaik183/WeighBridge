        	 function submitmyForm(){
			console.log("sss");
			var role = $('#role').val();
			var uid = $('#userId').val();
			var password = $('#password').val();

			$.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/check?role=' + role + '&userId=' + uid + '&password='
						+ password, // Replace with the path to your server-side script

				success : function(response) {
					
						alert(response);
						
					
											
				
				},
				error : function(error) {
					console.error("Error submitting the form:", error);
				}
			});
		}
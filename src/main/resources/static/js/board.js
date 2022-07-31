let index = {
	init: function() {
		$("#btn-save").on("click", () => {	
			this.save();
		});
	},


	save: function() {
		//alert('user의 save 함수 호출');
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		$.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), 	
			contentType: "application/json; charset=utf-8",
			dataType: "json" 
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));


		});
	}
}

index.init();
let index = {
	init: function() {
		$("#btn-save").on("click", () => {	
			this.save();
		});
		$("#btn-delete").on("click", () => {	
			this.deleteById();
		});
		$("#btn-update").on("click", () => {	
			this.update();
		});		
		$("#btn-reply-save").on("click", () => {	
			this.replySave();
		});	},


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
	},
	
	deleteById: function() {
		let id = $("#id").text();
		
		$.ajax({
			//회원가입 수행 요청
			type: "DELETE",
			url: "/api/board/"+id,
			dataType: "json" 
		}).done(function(resp) {
			alert("삭제 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	update: function() {
		let id =$("#id").val();
		
		//alert('user의 save 함수 호출');
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		console.log(id);
		console.log(data);
		$.ajax({
			//회원가입 수행 요청
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data), 	
			contentType: "application/json; charset=utf-8",
			dataType: "json" 
		}).done(function(resp) {
			alert("글 수정이 완료되었습니다.");
			console.log(resp);
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));


		});
	},
	
	replySave: function() {
		//alert('user의 save 함수 호출');
		let data = {
			userId:$("#userId").val(),
			boardId:$("#boardId").val(),
			content: $("#reply-contnet").val()
		};
	
		console.log(data);
		$.ajax({
			//댓글 
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data), 	
			contentType: "application/json; charset=utf-8",
			dataType: "json" 
		}).done(function(resp) {
			alert("댓글 작성이 완료되었습니다.");
			console.log(resp);
			location.href = `/board/${data.boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));


		});
	},
}

index.init();
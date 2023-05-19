/*
* 즉시실행함수로 만듦 (function(){ return {};}()
* 키와 메서드를 가진 JavaScript Object를 return함
* restful 방식으로 요청하기   
 */

var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");


function f1() {
	alert("함수호출");
}

var externalFunc = (function() {
	return {
		myname: "sol",
		mycompany: "신한",
		work: function() {
			alert("외부함수");
		}
	};
})(); // 익명함수를 즉시 실행함수로 만들었다. 


var replyManager = (function() {
	//특정 board의 댓글 가져오기 ==> replies/100
	var getAll2 = function(obj, callback) {
		console.log("get All.....");
		$.getJSON("/app/replies/" + obj, callback); //ajax코드랑 같다. 
	};
	
	function beforeSend (xhr){
				console.log(header);
				console.log(token);
				xhr.setRequestHeader(header, token);
	}

	//board의 댓글을 추가 {"bno": 11, "title":"aa", "writer":"bb"}
	var add2 = function(obj, callback) {
		//console.log("add.....");
		$.ajax({
			beforeSend: beforeSend,
			type: "post",
			url: "/app/replies/" + obj.bno,
			data: JSON.stringify(obj),
			dataType: "json",
			contentType: "application/json",
			success: callback
		});
	};
	var update2 = function(obj, callback) {
		$.ajax({
			beforeSend: beforeSend,
			type: "put", //update,
			url: "/app/replies/" + obj.bno,
			data: JSON.stringify(obj),
			dataType: "json",
			contentType: "application/json",
			success: callback
		});
	};

	var remove2 = function(obj, callback) {
		$.ajax({
			beforeSend: beforeSend,
			type: "delete",
			url: "/app/replies/" + obj.bno + " / " + obj.rno,
			dataType: "json",
			contentType: "application/json",
			success: callback
		});

	};
	return { getAll: getAll2, add: add2, update: update2, remove: remove2 };
})();


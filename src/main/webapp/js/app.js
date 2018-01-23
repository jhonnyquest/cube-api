var API_BASE_PATH = 'http://localhost:8080/cube/api/0_0_1/cube';

$(function () {
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
        xhr.setRequestHeader('user_token', token);
    });
});

function setTestCases(){	
	var user_token = (+new Date).toString(36);
	$('#user_token').val(user_token)
		
	$.ajax({
        url: API_BASE_PATH + '/' + $('#user_token').val() + '/test_cases',
        type: 'POST',
        data: JSON.stringify({
            test_cases: $('#test_cases').val()
        }),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(response) {
            if(response.success){
                $('#test_cases').val('');
                $('#test_cases_modal').modal('toggle');
                $('#settings_modal').modal('show');
            }else{
                alert(response.error);
            }
        },
        error: function (data) {
            alert(data.responseJSON.error);
        }
    });
    
}

function createCube(){
	$.ajax({
        url: API_BASE_PATH + '/' + $('#user_token').val(),
        type: 'POST',
        data: JSON.stringify({
            dimension: parseInt($('#matrix_dimension').val()),
            commands: parseInt($('#quantity_commands').val())
        }),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(response) {
        	if(response.success){
                $('#quantity_commands').val('')
                $('#matrix_dimension').val('');
                $('#settings_modal').modal('toggle');
                $('#calc_modal').modal('show');
            }else{
                alert(response.message);
            }
        },
        error: function (data) {
            alert(data.responseJSON.error);
        }
    });
}

function updateCube(){
    if($('#update').val() === '') {
        alert('Field "Update comand" cannot be empty');
        return;
    }

    var request = $('#update').val().split(" ");
    if(request.length < 4) {
        alert('data missing');
        return;
    }

    
    $.ajax({
        url: API_BASE_PATH + '/' + $('#user_token').val() + '/update',
        type: 'POST',
        data: JSON.stringify({
            x: parseInt(request[0]),
            y: parseInt(request[1]),
            z: parseInt(request[2]),
            value: parseFloat(request[3])
        }),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(response) {
        	if(response.success){
                alert('Cube successfully updated');
	            $('#update').val('');
	            $('#query').val('');
	            if(response.remaining_commands === 0){
	                alert('Next test case')
	                $('#calc_modal').modal('toggle');
	                $('#settings_modal').modal('show');
	            }else if(response.remaining_test_cases === 0){
	                $('#calc_modal').modal('toggle');
	                deleteCube();
	            }
        	}
        },
        error: function (data) {
        	alert(data.responseJSON.error);
        }
    });
}

function queryCube(){
    if($('#query').val() === '') {
        alert('Field "Update comand" cannot be empty');
        return;
    }

    var request = $('#query').val().split(" ");
    if(request.length < 5) {
        alert('data missing');
        return;
    }

    
    $.ajax({
        url: API_BASE_PATH + '/' + $('#user_token').val() + '/query',
        type: 'POST',
        data: JSON.stringify({
            x1: parseInt(request[0]),
            y1: parseInt(request[1]),
            z1: parseInt(request[2]),
            x2: parseInt(request[3]),
            y2: parseInt(request[4]),
            z2: parseInt(request[5])
        }),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(response) {
        	if(response.success){
        		$('#result').val(response.result);
        		alert('Cube successfully calculated');
	            $('#update').val('');
	            $('#query').val('');
	            if(response.remaining_commands === 0){
	                alert('Next test case')
	                $('#calc_modal').modal('toggle');
	                $('#settings_modal').modal('show');
	            }else if(response.remaining_test_cases === 0){
	                $('#calc_modal').modal('toggle');
	                deleteCube();
	            }
        	}
        },
        error: function (data) {
        	alert(data.responseJSON.error);
        }
    });
}

function deleteCube(){
    $('#update').val('');
    $('#query').val('');
    $('#quantity_commands').val('');
    $('#matrix_dimension').val('');
    $('#test_cases').val('');
    $.ajax({
        url: API_BASE_PATH + '/' + $('#user_token').val(),
        type: 'DELETE',
        success: function(result) {
        }
    });
}
var ProjectionsManager = {
		
		getAll : function(){
			$.get('ProjectionsServlet', function(data){
				$('#projectionsTable1').DataTable ({
					data: data.projections,
					paging: false,
					info: false,
					searching: false,
					autoWidth: true,
					columns: [
							{
							"data": 'movie',
							render:function(data, type, row){
								return '<a id="movieRedirectFromProjection" data-name="' + data + '" href="#">' + data + '</a>';
								}
							},
							{
								"data" : 'dateOutput',
								render:function(data, type, row){
									return '<a id="projectionRedirect" data-id="' + row.id +'" href="#">' + data + '</a>';
								}
									
							},
							{data : 'projectionType'},
							{data : 'theater'},
							{data : 'ticketPrice'}
						]
				})
			})
		},
		
		populateMovieDropdown : function () {
			
			$.get('MoviesServlet', function(data){
				var $dropdown = $('#newProjectionMovie');
				var newData = data.movies;
				$.each(newData, function(){
					$dropdown.append($("<option />").val(this.id).text(this.title));
				});
		});
			
		},
		
		populateProjectionTypeDrowpdown: function() {
			$.get('ProjectionTypeServlet', function(data){
				var $dropdown = $('#newProjectionType');
				var newData = data.projectionTypes;
				$.each(newData, function(){
					$dropdown.append($("<option />").val(this.id).text(this.name));
				})
			})
			
		},
		
		populateTheaterDropdown: function () {
			$.get('TheaterServlet', function(data){
				var $dropdown = $('#newProjectionTheater');
				var newData = data.theaters;
				$.each(newData, function(){
					$dropdown.append($("<option />").val(this.id).text(this.name));
				})
			})
		},
		
		addProjection : function() {
			var movie = $('#newProjectionMovie').val();
			var projectionType = $('#newProjectionType').val();
			var theater = $('#newProjectionTheater').val();
			var date = $('#newProjectionDate').val();
			var time = $('#newProjectionTime').val();
			var ticket = $('#newProjectionTicketPrice').val();
			params = {
					'action': 'add',
					'movie': movie,
					'projectionType': projectionType,
					'theater': theater,
					'date': date,
					'time': time,
					'ticket': ticket
			};
			$.post('ProjectionsServlet', params, function(data) {
				console.log(data);
			});
			
		},
		
		getProjection : function(ID){
			params = {
					'action': 'getProjection',
					'id': ID
			};
			$.post('ProjectionsServlet', params, function(data){
				$('#projectionTitle').append('Title: ' + '<a id="movieRedirectFromProjectionDetail" data-name="' + data.projection.movie + '" href="#">' + data.projection.movie + '</a>');
				$('#projectionDateAndTime').append('Date and time: ' + data.projection.dateOutput);
				$('#projectionType').append('Type: ' + data.projection.projectionType);
				$('#projectionTheater').append('Theater: ' + data.projection.theater);
				$('#projectionTicketPrice').append('Ticket price: ' + data.projection.ticketPrice);
				
			} );
			
		}
}


var MoviesManager = {
		
		getAll : function() {
			$.get('MoviesServlet', function(data) {
				$('#moviesTable1').DataTable({
					data: data.movies,
					paging: false,
					info: false,
					searching: false,
					autoWidth: true,
					columns: [
						{
							"data" : 'title',
							render:function(data, type, row){
								return '<a id="movieRedirect" href="#" data-id="' + row.id + '">' + data + '</a>';
							}
						},
						{data : 'duration'},
						{data : 'distributor'},
						{data : 'originCountry'},
						{data : 'yearOfProduction'},
						{
							"data": null,
							render:function(data, type, row)
							{
								return '<button id="deleteMovie" data-id="' + data.id + '">Delete</button>'
							},
							"targets": -1,
							"visible": false
						}
					]
				})
	
			});
		},

		searchMovies : function() {
			var $searchOptionBox = $('#searchOptionsBox').find('option:selected');
			var searchMovieInput = $('#searchMovieInput').val();
			var params = {
					'searchOptionsBox': $searchOptionBox.val(),
					'searchMovieInput': searchMovieInput
			};
			$('#moviesTable1').dataTable().fnClearTable();
			$.get('SearchMoviesServlet', params, function(data) {
				for(m in data.filteredMovies) {
					$('#moviesTable1').dataTable().fnDestroy();
					$('#moviesTable1').DataTable({
						data: data.filteredMovies,
						paging: false,
						info: false,
						searching: false,
						autoWidth: true,
						columns: [
							{
								"data" : 'title',
								render:function(data, type, row){
									return '<a id="movieRedirect" href="" data-id="' + row.id + '">' + data + '</a>';
								}
							},
							{data : 'duration'},
							{data : 'distributor'},
							{data : 'originCountry'},
							{data : 'yearOfProduction'},
							{
								"data": null,
								render:function(data, type, row)
								{
									return '<button id="deleteMovie" data-id="' + data.id + '">Delete</button>'
								},
								"targets": -1,
								"visible": false
							}
						]
					})
					
				}
			});
		},
		
		searchMoviesAdmin : function() {
			var $searchOptionBox = $('#searchOptionsBox').find('option:selected');
			var searchMovieInput = $('#searchMovieInput').val();
			var params = {
					'searchOptionsBox': $searchOptionBox.val(),
					'searchMovieInput': searchMovieInput
			};
			$('#moviesTable1').dataTable().fnClearTable();
			$.get('SearchMoviesServlet', params, function(data) {
				for(m in data.filteredMovies) {
					$('#moviesTable1').dataTable().fnDestroy();
					$('#moviesTable1').DataTable({
						data: data.filteredMovies,
						paging: false,
						info: false,
						searching: false,
						autoWidth: true,
						columns: [
							{
								"data" : 'title',
								render:function(data, type, row){
									return '<a id="movieRedirect" href="" data-id="' + row.id + '">' + data + '</a>';
								}
							},
							{data : 'duration'},
							{data : 'distributor'},
							{data : 'originCountry'},
							{data : 'yearOfProduction'},
							{
								"data": null,
								render:function(data, type, row)
								{
									return '<button id="deleteMovie" data-id="' + data.id + '">Delete</button>'
								},
								"targets": -1
							}
						]
					})
					
				}
			});
		},
		
		
		addMovie : function() {
			var title = $('#newMovieTitle').val();
			var duration = $('#newMovieDuration').val();
			var distributor = $('#newMovieDistributor').val();
			var originCountry = $('#newMovieOriginCountry').val();
			var yearOfProduction = $('#newMovieYearOfProduction').val();
			params = {
					'action': 'add',
					'title': title,
					'duration': duration,
					'distributor': distributor,
					'originCountry': originCountry,
					'yearOfProduction': yearOfProduction
			};
			$.post('MoviesServlet', params, function(data) {
				console.log(data);
			});
			
		},
		
		deleteMovie : function(ID) {
			params = {
					'action': 'delete',
					'id': ID
			};
			$.post('MoviesServlet', params, function(data){
				console.log(data);
			});
			
		},
		
		getMovie : function(ID) {
			params = {
					'action': 'getMovie',
					'id': ID
			};
			$.post('MoviesServlet', params, function(data){
				$("#updatedMovieTitle").val(data.movie.title);
				$("#updatedMovieDuration").val(data.movie.duration);
				$("#updatedMovieDistributor").val(data.movie.distributor);
				$("#updatedMovieOriginCountry").val(data.movie.originCountry);
				$("#updatedMovieYearOfProduction").val(data.movie.yearOfProduction);
				$('#movieTitle').append('Title: ' + data.movie.title);
				$('#movieDuration').append('Duration: ' + data.movie.duration);
				$('#movieDistributor').append('Distributor: ' + data.movie.distributor);
				$('#movieOriginCountry').append('Origin country: ' + data.movie.originCountry);
				$('#movieYearOfProduction').append('Year of production: ' + data.movie.yearOfProduction);
				
			} );
			
			
		},
		
		getMovieByTitle: function(title) {
			params = {
				'action': 'getMovieByTitle',
				'title': title
			};
			$.post('MoviesServlet', params, function(data){
				window.location.href = "#";
		        window.location.href += '?id=' + data.movie.id;
				$("#updatedMovieTitle").val(data.movie.title);
				$("#updatedMovieDuration").val(data.movie.duration);
				$("#updatedMovieDistributor").val(data.movie.distributor);
				$("#updatedMovieOriginCountry").val(data.movie.originCountry);
				$("#updatedMovieYearOfProduction").val(data.movie.yearOfProduction);
				$('#movieTitle').append('Title: ' + data.movie.title);
				$('#movieDuration').append('Duration: ' + data.movie.duration);
				$('#movieDistributor').append('Distributor: ' + data.movie.distributor);
				$('#movieOriginCountry').append('Origin country: ' + data.movie.originCountry);
				$('#movieYearOfProduction').append('Year of production: ' + data.movie.yearOfProduction);
			})
		},
		
		updateMovie : function(ID) {
			var title = $("#updatedMovieTitle").val();
			var duration = $("#updatedMovieDuration").val();
			var distributor = $("#updatedMovieDistributor").val();
			var originCountry = $("#updatedMovieOriginCountry").val();
			var yearOfProduction = $("#updatedMovieYearOfProduction").val();
			
			params = {
					'action': 'update',
					'id': ID,
					'title': title,
					'duration': duration,
					'distributor': distributor,
					'originCountry': originCountry,
					'yearOfProduction': yearOfProduction
			};
			$.post('MoviesServlet', params, function(data) {
				$("#updatedMovieTitle").val(data.movie.title);
				$("#updatedMovieDuration").val(data.movie.duration);
				$("#updatedMovieDistributor").val(data.movie.distributor);
				$("#updatedMovieOriginCountry").val(data.movie.originCountry);
				$("#updatedMovieYearOfProduction").val(data.movie.yearOfProduction);
				$('#movieTitle').append('Title: ' + data.movie.title);
				$('#movieDuration').append('Duration: ' + data.movie.duration);
				$('#movieDistributor').append('Distributor: ' + data.movie.distributor);
				$('#movieOriginCountry').append('Origin country: ' + data.movie.originCountry);
				$('#movieYearOfProduction').append('Year of production: ' + data.movie.yearOfProduction);
			});
		}
}

var UsersManager = {
		logIn : function() {
			var username = $('#LoginUsername').val();
			var password = $('#LoginPassword').val();
			
			params = {
					'username': username,
					'password': password
			};
			
			$.post('LoginServlet', params, function(data){
				if( data.user.role === "ADMIN") {
					StateManager.initState();
				}
				if( data.user.role === "USER") {
					StateManager.loggedInState();
				}
			})
		},
		
		logOut : function() {
			$.get('LogoutServlet', function(data) {
				console.log(data);
			})
		}
}

var StateManager = {
		
		initState : function(){
			params = {
					'action':'loggedInUserRole'
			};
			$.get('UserServlet', params, function(data){
				if(data.loggedInUserRole === "ADMIN") {
					$('#ProfileBtn').show();
					$('#UsersBtn').show();
					$('#LoginBtn').hide();
					$('#RegisterBtn').hide();
					$('#LogoutBtn').show();
					$('#LoginForm').hide();
					$('#RegisterForm').hide();
					$('#moviesSection').hide();
					$('#movieDetail').hide();
					$('#movieCommands').hide();
					$('#addProjectionForm').hide();
					$('#addNewProjection').show();
				}
				if(data.loggedInUserRole === "USER") {
					$('#ProfileBtn').show();
					$('#UsersBtn').hide();
					$('#LoginBtn').hide();
					$('#RegisterBtn').hide();
					$('#LogoutBtn').show();
					$('#LoginForm').hide();
					$('#RegisterForm').hide();
					$('#moviesSection').hide();
					$('#movieDetail').hide();
					$('#movieCommands').hide();
					$('#addProjectionForm').hide();
					$('#addNewProjection').hide();
				}
			})
			$('#ProfileBtn').hide();
			$('#UsersBtn').hide();
			$('#LogoutBtn').hide();
			$('#LoginForm').hide();
			$('#RegisterForm').hide();
			$('#moviesSection').hide();
			$('#movieDetail').hide();
			$('#movieCommands').hide();
			$('#addProjectionForm').hide();
			$('#addNewProjection').hide();
		},
		
		loggingState: function(){
			$('#ProfileBtn').hide();
			$('#UsersBtn').hide();
			$('#LogoutBtn').hide();
			$('#LoginForm').show();
			$('#RegisterForm').hide();
			$('#moviesSection').hide();
			$('#movieDetail').hide();
			$('#movieCommands').hide();
			$('#projectionsSection').hide();
		},
		
		registerState: function(){
			$('#ProfileBtn').hide();
			$('#UsersBtn').hide();
			$('#LogoutBtn').hide();
			$('#LoginForm').hide();
			$('#RegisterForm').show();
			$('#moviesSection').hide();
			$('#movieDetail').hide();
			$('#movieCommands').hide();
			$('#projectionsSection').hide();
			
		},
		
		moviesState: function() {
			params = {
					'action':'loggedInUserRole'
			};
			$.get('UserServlet', params, function(data){
				if(data.loggedInUserRole === "ADMIN") {
					$('#ProfileBtn').hide();
					$('#UsersBtn').hide();
					$('#LogoutBtn').hide();
					$('#LoginForm').hide();
					$('#RegisterForm').hide();
					$('#moviesSection').show();
					$('#addMovieForm').hide();
					$('#movieDetail').hide();
					$('#movieCommands').hide();
					$('#projectionsSection').hide();
					$('#addNewMovie').show();
					$('#ProfileBtn').show();
					$('#UsersBtn').show();
					$('#LogoutBtn').show();
					$('#moviesTable1').dataTable().fnSetColumnVis(5, true);
					
				}
				if(data.loggedInUserRole === "USER") {
					$('#ProfileBtn').hide();
					$('#UsersBtn').hide();
					$('#LogoutBtn').hide();
					$('#LoginForm').hide();
					$('#RegisterForm').hide();
					$('#moviesSection').show();
					$('#addMovieForm').hide();
					$('#movieDetail').hide();
					$('#movieCommands').hide();
					$('#projectionsSection').hide();
					$('#addNewMovie').hide();
					$('#ProfileBtn').show();
					$('#LogoutBtn').show();
				}
			})
			$('#ProfileBtn').hide();
			$('#UsersBtn').hide();
			$('#LogoutBtn').hide();
			$('#LoginForm').hide();
			$('#RegisterForm').hide();
			$('#moviesSection').show();
			$('#addMovieForm').hide();
			$('#movieDetail').hide();
			$('#movieCommands').hide();
			$('#projectionsSection').hide();
			$('#addNewMovie').hide();
			
		}
		
}




$(document).ready(function() {
	
	ProjectionsManager.getAll();
	ProjectionsManager.populateMovieDropdown();
	ProjectionsManager.populateProjectionTypeDrowpdown();
	ProjectionsManager.populateTheaterDropdown();
	
	StateManager.initState();
	
	MoviesManager.getAll();
	
	$('#searchMovieBtn').click(function(e) {
		e.preventDefault();
		params = {
				'action':'loggedInUserRole'
		};
		$.get('UserServlet', params, function(data){
			if(data.loggedInUserRole === "ADMIN") {
				MoviesManager.searchMoviesAdmin();
			}
			if(data.loggedInUserRole === "USER"){
				MoviesManager.searchMovies();
			}
			
		});
		MoviesManager.searchMovies();
	});
	
	$('#btnConfirmLogin').click(function(e) {
		e.preventDefault();
		UsersManager.logIn();
		location.reload();
	});
	
	$('#LogoutBtn').click(function(e) {
		e.preventDefault();
		UsersManager.logOut();
		location.reload();
	});
	
	
	
	$('#addNewMovie').click(function(e) {
		$('#addMovieForm').toggle();
	});
	
	$('#submitNewMovie').click(function(e) {
		e.preventDefault();
		MoviesManager.addMovie();
		$('#addMovieForm').toggle();
		$('#moviesTable1').dataTable().fnDestroy();
        MoviesManager.getAll();
	});
	
	$('#submitNewProjection').click(function(e){
		ProjectionsManager.addProjection();
		$('#projectionsTable1').dataTable().fnDestroy();
		ProjectionsManager.getAll();
	})
	
	$('#btnConfirmLogin').click(function(e) {
		e.preventDefault();
		MoviesManager.searchMovies();
		console.log('login');
	});
	
	$('#LoginBtn').click(function(e) {
		e.preventDefault();
		StateManager.loggingState();
	});
	
	$('#MoviesBtn').click(function (e) {
		e.preventDefault();
		StateManager.moviesState();
		
	});
	
	$('#RegisterBtn').click(function(e) {
		e.preventDefault();
		StateManager.registerState();
	});
	
	$('#HomeBtn').click(function(e){
		e.preventDefault();
		location.reload();
	});
	
	
    $('body').on('click', '#deleteMovie', function(e){
        var id= $(this).attr("data-id");
        MoviesManager.deleteMovie(id);
        $(this).parents("tr").remove();
        $('#moviesTable1').dataTable().fnDestroy();
        MoviesManager.getAll();
        
        
        
    });
    
    $('body').on('click', '#movieRedirect', function(e){
        var id= $(this).attr("data-id");
        e.target.href = "#";
        e.target.href += '?id=' + id;
        $('#movieTitle').text('');
		$('#movieDuration').text('');
		$('#movieDistributor').text('');
		$('#movieOriginCountry').text('');
		$('#movieYearOfProduction').text('');
        MoviesManager.getMovie(id);
        $('#movieDetail').show();
		$('#movieCommands').show();
		$('#updateMovieForm').hide();
		$('#moviesSection').hide();
    });
    
    
    
    $('body').on('click', '#movieRedirectFromProjection', function(e){
        var name= $(this).attr("data-name");
        $('#movieTitle').text('');
		$('#movieDuration').text('');
		$('#movieDistributor').text('');
		$('#movieOriginCountry').text('');
		$('#movieYearOfProduction').text('');
		MoviesManager.getMovieByTitle(name);
        $('#movieDetail').show();
		$('#movieCommands').show();
		$('#updateMovieForm').hide();
		$('#projectionsSection').hide();
		console.log(name);
      
    });
    
    $('body').on('click', '#movieRedirectFromProjectionDetail', function(e){
        var name= $(this).attr("data-name");
        $('#movieTitle').text('');
		$('#movieDuration').text('');
		$('#movieDistributor').text('');
		$('#movieOriginCountry').text('');
		$('#movieYearOfProduction').text('');
		MoviesManager.getMovieByTitle(name);
        $('#movieDetail').show();
		$('#movieCommands').show();
		$('#updateMovieForm').hide();
		$('#moviesSection').hide();
		$('#projectionsSection').hide();
		$('#projectionDetail').hide();
		console.log(name);
      
    });
    
    $('body').on('click', '#projectionRedirect', function(e){
        var id= $(this).attr("data-id");
        e.target.href = "#";
        e.target.href += '?id=' + id;
        ProjectionsManager.getProjection(id);
        $('#projectionsSection').hide();
        
    });
    
    $('#updateMovieFromDetail').click(function(e) {
		$('#updateMovieForm').toggle();
	});
    
    $('#deleteMovieFromDetail').click(function(e) {
    	var baseUrl = (window.location).href;
    	var id = baseUrl.substring(baseUrl.lastIndexOf('=') + 1);
		MoviesManager.deleteMovie(id);
		$('#moviesTable1').dataTable().fnDestroy();
        MoviesManager.getAll();
        $('#movieDetail').hide();
		$('#movieCommands').hide();
		$('#moviesSection').show();
		$('#addMovieForm').hide();
    });
    
    $('#updateMovie').click(function(e) {
    	var baseUrl = (window.location).href;
    	var id = baseUrl.substring(baseUrl.lastIndexOf('=') + 1);
    	$('#movieTitle').text('');
		$('#movieDuration').text('');
		$('#movieDistributor').text('');
		$('#movieOriginCountry').text('');
		$('#movieYearOfProduction').text('');
		MoviesManager.updateMovie(id);
		$('#updateMovieForm').hide();
		$('#moviesTable1').dataTable().fnDestroy();
		MoviesManager.getAll();
		
	});
    
    $('#addNewProjection').click(function(e) {
    	$('#addProjectionForm').toggle();
    });
	
	
	
	
});
let GRID_W = 20;
let GRID_H = 20;

let gridTable = [];
let grid = [];
let table = document.getElementById("grid");

let tempGrid = [];


let nextGrid = {
	cont: document.getElementById("nextGrid"),
	gridTd: [],
	grid: []
};


//Create grid
for (let i = 0; i < GRID_W; i++){
    gridTable[i] = [];
	grid[i] = [];
	nextGrid.gridTd[i] = [];
	nextGrid.grid[i] = [];
	tempGrid[i] = [];
}




addGrid(table, gridTable, grid, false);
addGrid(nextGrid.cont, nextGrid.gridTd, nextGrid.grid, true);

function addGrid(gridCont, tdMatrix, gridmatrix, isClick){
	for (let i = 0; i < GRID_W; i++) {
	    let tr = document.createElement('tr');
	    for (let j = 0; j < GRID_H; j++) {
	        let td = document.createElement('td');
	        tr.appendChild(td);
			$(td).addClass("cell");
			
			//add clicable cell to activate and disactivate it
			if(isClick){
				$(td).click((cell) => {
					
					//console.log(cell.currentTarget);
					//flip cell
					gridmatrix[j][i] = !gridmatrix[j][i]; 
					
					if(gridmatrix[j][i]){
						$(cell.currentTarget).css("background-color", "#0d00ff");
					}else{
						$(cell.currentTarget).css("background-color", "#AAA");
					}
				});
			}
	        tdMatrix[j][i] = $(td);
			gridmatrix[j][i] = false;
	    }

	    gridCont.appendChild(tr);
	}
}


/*
for (let i = 0; i < GRID_W; i++) {
    let tr = document.createElement('tr');
    for (let j = 0; j < GRID_H; j++) {
        let td = document.createElement('td');
        tr.appendChild(td);
		
		//add clicable cell to activate and disactivate it
		$(td).click((cell) => {
			
			console.log(cell.currentTarget);
			//flip cell
			grid[j][i] = !grid[j][i]; 
			
			if(grid[j][i]){
				$(cell.currentTarget).css("background-color", "#0d00ff");
			}else{
				$(cell.currentTarget).css("background-color", "#AAA");
			}
		});

        gridTable[j][i] = $(td);
		grid[j][i] = false;
    }
    table.appendChild(tr);
}
*/

//gridTable[19][10].css("background-color", "#F00");




function updateGrid(){
	for (let i = 0; i < GRID_W; i++) {
	    for (let j = 0; j < GRID_H; j++) {
			if(grid[i][j]){
				gridTable[i][j].css("background-color", "#0d00ff");
			}else{
				gridTable[i][j].css("background-color", "#AAA");
			}
	    }
	}
}


function updateGridNext(){
	for (let i = 0; i < GRID_W; i++) {
	    for (let j = 0; j < GRID_H; j++) {
			if(nextGrid.grid[i][j]){
				nextGrid.gridTd[i][j].css("background-color", "#0d00ff");
			}else{
				nextGrid.gridTd[i][j].css("background-color", "#AAA");
			}
	    }
	}
}





function setGrid(fromGrid){	
	
	console.log("wd nwdkwnkdndwk");
	
	copyGridTo(nextGrid.grid, grid);	
	updateGrid();
	
	copyGridTo(fromGrid, nextGrid.grid);
	updateGridNext();
}


function setBackGrid(fromGrid){
	
	for (let i = 0; i < GRID_W; i++) {
	    for (let j = 0; j < GRID_H; j++) {
			if(grid[i][j]){
				if(grid[i][j] == fromGrid[i][j])
					gridTable[i][j].css("background-color", "#00ff00");
				else
					gridTable[i][j].css("background-color", "#bf8080");
			}else{
				if(nextGrid.grid[i][j] == fromGrid[i][j])
					gridTable[i][j].css("background-color", "#8eb88e");
				else
					gridTable[i][j].css("background-color", "#ff0000");
			}
	    }
	}
	copyGridTo(fromGrid, grid);	
}









function copyGridTo(from, to){
	for(let y = 0; y<GRID_H; y++){
		for(let x = 0; x<GRID_W; x++){
			to[x][y] = from[x][y];
		}
	}
}



//next button handler

$("#nextStep").click(()=>{
	let req = {
		grid: JSON.stringify(nextGrid.grid)
	};
    $.post( "next", req, function() {
        console.log( "Request sended" );
      })
        .done(function(data) {
          	console.log(JSON.parse(data));
			setGrid(JSON.parse(data));
        });
});

$("#dataset").click(()=>{
	let req = {
		grid: JSON.stringify(grid)
	};
    $.post( "testSet", req, function() {
        console.log( "Request sended" );
      })
        .done(function(data) {
          	console.log(JSON.parse(data));
			let grid1 = JSON.parse(data);
			$.post( "testSet", req, function() {
		        console.log( "Request sended" );
		      })
		        .done(function(data) {
		          	console.log(JSON.parse(data));
					setGrid(grid1);
					setGrid(JSON.parse(data));
		        });
        });
});


/*
$("#build").click(()=>{
	let req = {
		grid: JSON.stringify(grid)
	};
    $.post( "build", req, function() {
        console.log( "Request sended" );
      });
});
*/


$("#backStep").click(()=>{
	
	let req = {
		grid: JSON.stringify(nextGrid.grid)
	};
    $.post( "back", req, function() {
        console.log( "Request sended" );
      })
        .done(function(data) {
          	console.log(JSON.parse(data));
			setBackGrid(JSON.parse(data));
        });
});



let emptyGrid = [];
for (let i = 0; i < GRID_W; i++) {
	emptyGrid[i] = [];
    for (let j = 0; j < GRID_H; j++) {
	emptyGrid[i][j] = false;
	}
}
$("#clear").click(()=>{
	setGrid(emptyGrid);
	setGrid(emptyGrid);
});














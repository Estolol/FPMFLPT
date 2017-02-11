#Dimensions parameters
param Rmax integer, >= 0;
param Cmax integer, >= 0;
set R := 0..(Rmax-1);
set C := 0..(Cmax-1);

#K number
param Kmax integer, >= 0;
set K := 1..Kmax;

#Ingredients
param T{R,C} binary;

#Constraints parameters
param L integer, >=1;
param H integer, >=5;

var rmin{K} integer;
var rmax{K} integer;
var cmin{K} integer;
var cmax{K} integer;
var x{R, C, K} binary;

maximize cells: sum{i in R, j in C, k in K} x[i,j,k] ;

subject to domainRows0{k in K}: rmin[k] >= 0;
subject to domainRows1{k in K}: rmin[k] <= Rmax - 1;
subject to domainRows2{k in K}: rmax[k] >= 0;
subject to domainRows3{k in K}: rmax[k] <= Rmax - 1;
subject to domainCols0{k in K}: cmin[k] >= 0;
subject to domainCols1{k in K}: cmin[k] <= Cmax - 1;
subject to domainCols2{k in K}: cmax[k] >= 0;
subject to domainCols3{k in K}: cmax[k] <= Cmax -1;

subject to coherentK0{k in K}: rmin[k] <= rmax[k];
subject to coherentK1{k in K}: cmin[k] <= cmax[k];

subject to noIntersection1{i in R, j in C}: sum{k in K}x[i,j,k] <= 1;

subject to coherentRowXijk0{i in R, j in C, k in K}: x[i,j,k] * i >= x[i,j,k] * rmin[k];
subject to coherentRowXijk1{i in R, j in C, k in K}: x[i,j,k] * i <= x[i,j,k] * rmax[k];
subject to coherentColXijk0{i in R, j in C, k in K}: x[i,j,k] * j >= x[i,j,k] * cmin[k];
subject to coherentColXijk1{i in R, j in C, k in K}: x[i,j,k] * j <= x[i,j,k] * cmax[k];

subject to noHoles{i in R, j in C, k in K}: (1 - x[i,j,k]) * i <= (1 - x[i,j,k]) * (rmin[k] - 1) or (1 - x[i,j,k]) * i >= (1 - x[i,j,k]) * (rmax[k] + 1) or (1 - x[i,j,k]) * j <= (1 - x[i,j,k]) * (cmin[k] - 1) or (1 - x[i,j,k]) * j >= (1 - x[i,j,k]) * (cmax[k] + 1);

subject to tomatoes{k in K}: sum{i in R, j in C} x[i,j,k] * T[i,j] >= L;
subject to mushrooms{k in K}: sum{i in R, j in C} x[i,j,k] * (1 - T[i,j]) >= L;

subject to sliceSize{k in K}: sum{i in R, j in C} x[i,j,k] <= H;

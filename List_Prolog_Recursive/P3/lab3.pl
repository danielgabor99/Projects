%a. Replace all occurrences of an element from a list with another element e.
%replace(List -list, NewList -List, E- int, NewE-int)
%replace(i,o,i,i)
%List- the actual list
%NewList- the list that will be created
%E- the values that will be changed
%NewE- the changing value
%{
%     [], if List=[]
%     NewE U NewList, if H = E
%     H U NewList, if H !=E (otherwise
%}


replace([],[],_,_).
replace([H|T],LCopy,E,NewE):-H is E, replace(T,NewList,E,NewE),LCopy=[NewE|NewList].
replace([H|T],LCopy,E,NewE):-replace(T,NewList,E,NewE),LCopy=[H|NewList].



%findMax(L-list, Z-int, M-int)
%findMax(i,i,o)
%List- the actual list
%Z- the maximum
%M- the output of maximum
%{
%     max, if List is empty
%     max=H, if max<H
%     continue, otherwise
%}

findMax([],Z,Z).
findMax([H|T],Z,M):-number(H),Z<H, Z1 is H,findMax(T,Z1,M).
findMax([_|T],Z,M):-findMax(T,Z,M).

% b. For a heterogeneous list, formed from integer numbers and list of
% numbers, define a predicate to determine
% the maximum number of the list, and then to replace this value in
% sublists with the maximum value of sublist.
%changeMax(List- list,newList- list, X- int)
%changeMax(i,o,i)
%{
%        [], if List is empty
%        H U newList AND updates Max, if max>H
%        H U newList, if max<=H
%        modifiedList U NewList, if H is list
%}


changeMax([],[],_).
changeMax([H|T],LCopy,X):-number(H),findMax([H|T],0,M),X<M, X1 is M, changeMax(T,NewList,X1),LCopy=[H|NewList].
changeMax([H|T],LCopy,X):-number(H),findMax([H|T],0,M),X>=M, changeMax(T,NewList,X),LCopy=[H|NewList].
changeMax([H|T],LCopy,X):-is_list(H),findMax(H,0,Y),replace(H,ModifiedList,X,Y), changeMax(T,NewList,X),LCopy=[ModifiedList|NewList].


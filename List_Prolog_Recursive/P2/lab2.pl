%isInList(L:list,E:int)
%L1: the search list
%E: the element
%flow model(i,i)
isInList([H|T],E):-E=\=H, isInList(T,E).
isInList([H|_],E):-E=:=H.

%a. Write a predicate to determine the difference of two sets
%the difference of two sets
%difference(LA:list, LB:list, LD:list)
%L1- first list
%L2- second list
%LD- diference list
%flow model(i,i,o)
%difference(LA,LB):
%{
%     [], LB is empty
%     LB1 U difference(LA,LB2..LBN) if LB1 is not in list LA
%     diferrence(LA,LB2..LBN, LD) otherwise
%}
%
difference([],_,[]).
difference([H|T],LB,LD):-isInList(LB,H),difference(T,LB,LD).
difference([H|T],LB,LD):-difference(T,LB,LN),LD=[H|LN].


%b. Write a predicate to add value 1 after every even element from a list.
%insertAfterEven(L:list, Lcopy:list,E1: int)
%flow model(i,o,i)
%insertAfterEven(L,E1):
%{
%     [], if L is empty
%     L1 U E1 U insertAfterEven(L2..LN,E1), IF L1 is even
%     L1 U insertAfterEven(L2..LN,E1), otherwise
%}
insertAfterEven(L,[],_):- L=[].
insertAfterEven([H|T],Lcopy,E1):-H mod 2 =:= 0,insertAfterEven(T,LN,E1),Lcopy=[H,E1|LN].
insertAfterEven([H|T],Lcopy,E1):-insertAfterEven(T,LN,E1),Lcopy=[H|LN].
























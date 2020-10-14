% subsets(l1...ln) =
%	[], n = 0
%	l1 + subsets(l2...ln), n > 0
%	subsets(l2...ln), n > 0

% subsets(L:list, R:list)
% subsets(i, o)

subsets([], []).
subsets([H|T], [H|R]) :-subsets(T, R).
subsets([_|T], R) :-subsets(T, R).

% suma(l1...ln, c) =
%	c, n = 0
%	suma(l2...ln, c + l1), otherwise

% suma(L:list, C:number, R:number)
% suma(i, i, o)

suma([], C, C).
suma([H|T], C, R) :-NC is C + H,suma(T, NC, R).


% onesolution(L:list, N:number, R:list)
% onesolution(i, i, o)

onesolution(L, S, R) :- subsets(L, R),suma(R, 0, S).

% allsolutions(L:list, S:number, R:list)
% allsolutions(i, i, o)

allsolutions(L, S, R) :-findall(RPartial, onesolution(L, S, RPartial), R).

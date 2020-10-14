'''
inclusion(lA,lB) {
                    true, lA1=[]
                    false, isInLIst(lA1, LB)=false
                    inclusion(lA2...lAn,lB) , isInLIst(lA1, LB)=true
                }
                isInLIst(lA1, lB1...lBn){
                                        false, lB=[]
                                        isInList(lA1, lB2...lBn), lA1=!lB1
                                        true, lA1==lB1
                                        }

insert(l1..ln,e,e1) { [] , list is empty
                      l1Ue1Uinsert(l2..ln,e,e1)  ,l1=e
                      insert(l2...ln,e,e1), otherwise
'''
def isInList(lA1,lB):
    if lB==[]:
        return False
    if lA1!=lB[0]:
        return isInList(lA1, lB[1:])
    if lA1==lB[0]:
        return True

def inclusion(lA,lB):
        if(lA==[]):
            return True
        if isInList(lA[0],lB)==False:
            return False
        if isInList(lA[0],lB)==True:
            return inclusion(lA[1:],lB)
def lab1():
    print(inclusion([1,2,3],[1,3,4]))
    l=[1,2,3]
    print(insert([1,2,3],2,9))
    print(l)

def insert(list1,e,e1):
    if len(list1)==0:
        return []
    if(list1[0]==e):
        return [list1[0]]+ insert(list1[1:],e,e1)
    else:
        [list1[0]]+insert(list1[1:], e, e1)
lab1()
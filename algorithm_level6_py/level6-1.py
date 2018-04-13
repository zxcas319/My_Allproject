def hopscotch(board, size):
    result = 0
    temp1=[]
    temp2=[]
    # 땅따먹기 게임으로 얻을 수 있는 최대 점수는?
    for i in range(size-1):
        if board[i].index(max(board[i]))!=board[i+1].index(max(board[i+1])):
            result+=max(board[i])
            print("0.",max(board[i]))
            if i==size-2:
                print("3")
                result+=max(board[size])
        else:
            temp1+=board[i]
            temp2+=board[i+1]
            temp1.reverse()
            temp2.reverse()
            if temp1[1]-temp1[0]<=temp2[1]-temp2[0]:
                board[i+1].remove(max(board[i+1]))
                result+=temp1[0]
                print("1.",temp1[0])
                if i==size-2:
                    result+=temp2[0]
            else:
                result+=temp1[1]
                print("2",temp1[1])
                if i==size-2:
                    result+=temp2[1]
    return result


#아래는 테스트로 출력해 보기 위한 코드입니다.
board =  [[ 1, 2, 3, 5 ], [ 5, 6, 7, 8 ], [4, 3, 2, 1]]
print(hopscotch(board, 3))
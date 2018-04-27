def hopscotch(board, size):
    result = 0
    
    for i in range(1,size):
        for idx1,down in enumerate(board[i]):
            board[i][idx1]=max(board[i-1][:idx1]+board[i-1][idx1+1:])+board[i][idx1]
            #밑의 행의 값을 위의 행에서 같은 열이 아닌 것중 최대값을 더한 값으로 갱신
            #이후 갱신된 값과 그 다음 행을 비교해서 그 열에서 최대로 될 수 있는 값에서 비교할  
            #수 있다
            print(board[i])
            #땅따먹기 게임으로 얻을 수 있는 최대 점수는?
    return max(board[size-1])


#아래는 테스트로 출력해 보기 위한 코드입니다.
board =  [[ 1, 2, 3, 5 ], [ 5, 6, 7, 8 ], [4, 3, 2, 1]]
print(hopscotch(board, 3))
"""
밑의 행의 값을 위의 행에서 같은 열이 아닌 것중 최대값을 더한 값으로 갱신
이후 갱신된 값과 그 다음 행을 비교해서 그 열에서 최대로 될 수 있는 값에서 비교할  
수 있다

자신의 행을 제외한 값 중 max 함수로 최대인 것을 골라내는 작업을 통해 포문 하나를 줄임

"""
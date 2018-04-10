"""

가장 큰 정사각형 찾기 Level 4

O와 X로 채워진 표가 있습니다. 표 1칸은 1 x 1 의 정사각형으로 이루어져 있습니다.
 표에서 O로 이루어진 가장 큰 정사각형을 찾아 넓이를 반환하는 findLargestSquare 함수를 완성하세요.
예를 들어
1	2	3	4	5
X	O	O	O	X
X	O	O	O	O
X	X	O	O	O
X	X	O	O	O
X	X	X	X	X

 라면
 1	2	3	4	5
X	O	O	O	X
X	O	O	O	O
X	X	O	O	O
X	X	O	O	O
X	X	X	X	X
가 되며 넓이는 9가 되므로 9를 반환해 주면 됩니다.

"""
def findLargestSquare(board):
    max1=0
    board_init=[[0]*len(board[0]) for i in board]
    for i,sub_board in enumerate(board):
        for j,rev in enumerate(sub_board):
            if rev=='X':
                continue
            board_init[i][j]=num_change(board_init,i,j)
            max1=max(board_init[i][j],max1)
    return max1**2
def num_change(board_init,i,j):
    a=board_init[i][j-1]
    b=board_init[i-1][j]
    c=board_init[i-1][j-1]
    return min(a,b,c)+1

#아래 코드는 출력을 위한 테스트 코드입니다.

testBoard = [['X','O','O','O','X'],['X','O','O','O','O'],['X','X','O','O','O'],['X','X','O','O','O'],['X','X','X','X','X']]
print(len(testBoard))
print(len(testBoard[0]))
print(findLargestSquare(testBoard))
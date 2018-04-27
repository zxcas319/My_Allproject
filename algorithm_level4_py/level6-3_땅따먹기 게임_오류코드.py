"""
error!! 이코드는 잘못된 코드입니다.


땅따먹기 게임 Level 4


영희는 땅따먹기 게임에 푹 빠졌습니다. 땅따먹기 게임의 땅은 총 N행 4열로 나누어져 있고, 모든 칸에는 점수가 쓰여 있습니다. 땅을 밟으면서 한 행씩 내려올 때, 영희는 각 행의 4칸 중 1칸만 밟으면서 내려올 수 있습니다. 땅따먹기 게임에는 같은 열을 연속해서 밟을 수가 없는 특수 규칙이 있습니다. 즉, 1행에서 (5)를 밟았다면, 2행의 (8)은 밟을 수가 없게 됩니다. 마지막 행까지 모두 내려왔을 때, 점수가 가장 높은 사람이 게임의 승자가 됩니다. 여러분이 hopscotch 함수를 제작하여 영희가 최대 몇 점을 얻을 수 있는지 알려주세요. 예를 들어
1 2 3 5
 5 6 7 8
 4 3 2 1
의 땅이 있다면, 영희는 각 줄에서 (5), (7), (4) 땅을 밟아 16점을 최고점으로 받을 수 있으며, hopscotch 함수에서는 16을 반환해주면 됩니다.


"""

def hopscotch(board, size):
    result = 0
    
    # 땅따먹기 게임으로 얻을 수 있는 최대 점수는?
    for i in range(size-1):
        if board[i].index(max(board[i]))!=board[i+1].index(max(board[i+1])):
            result+=max(board[i])
            print("0.",max(board[i]))
            if i==size-2:
                print("3",max(board[size-1]))
                result+=max(board[size-1])
        else:
            print("^^")
            temp1=[]#list1[:]
            temp2=[]
            temp1+=board[i]
            temp2+=board[i+1]
            temp1.sort()
            temp2.sort()
            if temp1[-1]-temp1[-2]>=temp2[-1]-temp2[-2]:
                board[i+1].remove(max(board[i+1]))
                print(board[i+1])
                result+=temp1[-1]
                print("1.",temp1[-1])
                if i==size-2:
                    result+=temp2[-2]
            else:
                result+=temp1[-2]
                print("2",temp1[-1])
                if i==size-2:
                    result+=temp2[-1]
    return result

"""

이코드는 잘못 구현되었다

처음에는 위의 최대값과 밑의 최대값이 같은 열에 있으면 안되기 때문에 같은 열에 있는 경우 

그 행에서 제일 큰값과 그행에서 두번째로 큰 값의 차이가 가장 큰 것을 택해야 한다고 생각했다

그래서 그 조건을 따라 코드를 작성하였다. 문제는 두 행의 같은 열의 있는 최대값과 두번째값의

차이가 같을 때 선택하게 되는 숫자에 따라 값이 달라 질 수 있다는 점이 반례였다.

예)
5    6
7    8
13  13 교차 선택시
내코드로 하면 위의 5,8 만 선택함 근데  6,7 선택한 값이 답이 될 수 있음

"""
#아래는 테스트로 출력해 보기 위한 코드입니다.
board =  [[ 1, 2, 3, 5 ], [ 5, 6, 7, 8 ], [4, 3, 2, 1]]
print(hopscotch(board, 3))
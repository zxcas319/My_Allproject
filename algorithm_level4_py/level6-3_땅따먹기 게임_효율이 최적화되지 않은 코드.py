def hopscotch(board, size):
    # 땅따먹기 게임으로 얻을 수 있는 최대 점수는?
    sum_case = [0, 0, 0, 0]

    for row in board:
        tmp = sum_case[:]
        print(tmp, row)
        for i in range(len(row)):
            sum_case[i] = row[i] + max(tmp[:i] + tmp[i+1:])

    return max(sum_case)

#아래는 테스트로 출력해 보기 위한 코드입니다.
board =  [[ 1, 2, 3, 5 ], [ 5, 6, 7, 8 ], [4, 3, 2, 1]]
print(hopscotch(board, 3))
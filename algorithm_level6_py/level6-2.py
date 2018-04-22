"""

과자 많이 먹기 Level 6


과자를 좋아하는 동우는 책상 위에 일렬로 놓아진 과자를 발견하였습니다. 과자에는 맛을 숫자로 평가한 종이가 붙어 있습니다. 동우는 맨 왼쪽부터 순서대로 과자를 먹기로 하였습니다. 동우는 먹을 과자를 고를 때 이전에 먹은 과자보다 맛이 더 좋은 과자만 먹습니다. 

제일 처음에 맛이 3인 과자를 먹었다면, 다음에는 4보다 작은 맛의 과자는 먹지 않습니다. 

책상위에 놓인 과자의 맛이 입력되면, 동우가 최대 과자를 몇 개를 먹을 수 있는지 반환해주는 eatCookie 함수를 완성하세요.

예를 들어 [1, 4, 2, 6, 3, 4, 1, 5] 가 입력된다면 동우는 1, 3, 5, 6, 8번째 과자(각각의 맛은 1, 2, 3, 4, 5)를 골라 총 5개를 먹을 수 있고, 5개보다 더 많이 먹을 수 있는 방법은 없으므로 5를 리턴하면 됩니다.

"""

def eatCookie(cookies):
    answer = 0
    valid = [1]*len(cookies)
    
    for i, cur_cookies in enumerate(cookies) :
        standard = 1
        for j, prev_cookies in enumerate(cookies[:i]):
            if valid[j] == standard and cur_cookies > prev_cookies :
                # 비교하려는 값까지의 연속된 작은 수 갯수와 현재 위치의 값의 크기 비교
                standard +=  1
                valid[i] = standard
        print(valid)
            
    return max(valid)



# 아래는 테스트로 출력해 보기 위한 코드입니다.
# 10
print(eatCookie([1, 4, 2, 6, 3, 4, 1, 5]))

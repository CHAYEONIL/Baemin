# 면적 반환 기능 = 평 , m2 1평 = 3.3
# 길이 변환기능 = cm, inch 1inch = 2.54cm
# Q, q 입력시 종료

def area_change():
    print("평(p)과 m^2(m)에서 변환할 값을 선택해주세요")

    a_change_value = input()

    if a_change_value == 'p':
        print("m^2로 변환될 값을 입력해주세요")
        m_change_value = float(input())
        output_value = 3.3 * m_change_value

        print("변환값 :", output_value,  "m^2") 
    elif a_change_value == 'm':
        print("평으로 변환될 값을 입력해주세요")
        p_change_value = float(input())
        output_value = p_change_value / 3.3

        print("변환값 :", output_value,"평")
    else :
        print("잘못 입력하셨습니다.")

    # print(a_change_value)

def length_change():
    print("cm(cm)과 inch(inch)에서 변환할 값을 선택해주세요")

    l_change_value = input()

    if l_change_value == 'cm':
        print("inch로 변환할 값을 입력해주세요")
        inch_change_value = float(input())
        output_value = inch_change_value / 2.54

        print("변환값 :", output_value,"inch") 
    elif l_change_value == 'inch':
        print("cm으로 변환할 값을 입력해주세요")
        cm_change_value = float(input())
        output_value = cm_change_value * 2.54

        print("변환값 :", output_value,"cm")
    else :
        print("잘못 입력하셨습니다.")

    # print(a_change_value)

while(1):
    print("면적변환기능(area)과 길이변환기능(length), 종료(Q, q) 선택해주세요")
    input_value = input()

    if input_value == 'area' :
        area_change()
    elif input_value == "length":
        length_change()
    elif input_value == "Q" or "q":
        print("종료합니다.")
        break;
    else:
        print("잘못 입력하셨습니다.")
# print(input_value)

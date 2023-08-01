class 편의점:
    def __init__(self, dic):
        self.상품목록 = dic

    def show(self):
        for k, v in range(편의점):
            print(f'{k} : {v} \\')
cu = 편의점({커피:1000}, {우유:400}, {도넛:700})
cu.show()
